package com.adSystems.services;

import com.adSystems.datas.models.Listing;
import com.adSystems.datas.models.ListingStatus;
import com.adSystems.datas.models.SubscriptionPlan;
import com.adSystems.datas.models.User;
import com.adSystems.datas.repositories.ListingRepository;
import com.adSystems.datas.repositories.UserRepository;
import com.adSystems.dtos.reponses.ListingResponse;
import com.adSystems.dtos.reponses.UpdateListingResponse;
import com.adSystems.dtos.requests.ListingRequests;
import com.adSystems.dtos.requests.UpdateListingRequest;
import com.adSystems.exception.ListingLimitExceededException;
import com.adSystems.exception.ListingNotFoundException;
import com.adSystems.exception.UserNotFoundException;
import com.adSystems.exception.ValidationException;
import com.adSystems.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

import static com.adSystems.util.Mapper.*;
import static com.adSystems.validation.Validations.validateCreateListing;


@AllArgsConstructor
@Service
public class ListServicesImplementation implements ListingServices{
    @Autowired
    private ListingRepository listingRepository;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ListingResponse createListing(ListingRequests request) {
        validateCreateListing(request);
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException("User not found"));
        if(user.getSubscriptionPlan() == SubscriptionPlan.FREE && user.getListingCount() >= 3) throw new ListingLimitExceededException("Upgrade to premium to post more listing");
        Listing listing = Mapper.mapToCreateAListing(request);
        listing.setUserId(user.getId());
        listingRepository.save(listing);

        user.setListingCount(user.getListingCount() + 1);
        userRepository.save(user);

        return Mapper.mapToCreateListingResponse();
    }

    @Override
    public ListingResponse addImage(String listingId, String token, MultipartFile file) {
        Listing listing = listingRepository.findByIdAndEditToken(listingId, token).orElseThrow(() -> new ValidationException("Invalid token"));
        String imageUrl = imageService.uploadImage(listingId, file);

        listing.getImages().add(imageUrl);
        listingRepository.save(listing);
        return mapToCreateListingResponse();
    }




    @Override
    public Listing getById(String id) {
        if(id == null || id.trim().isEmpty()) throw new ValidationException("Listing id cannot be empty");
       return listingRepository.findById(id).filter(listing -> listing.getStatus() == ListingStatus.ACTIVE).orElseThrow(() -> new ListingNotFoundException("Listing not found!"));
    }

    @Override
    public List<Listing> getByCity(String cityId) {
        if(cityId == null || cityId.trim().isEmpty()) throw new ValidationException("City id cannot be empty");
        return listingRepository.findByCityIdAndStatusOrderByCreatedAtDesc(cityId, ListingStatus.ACTIVE);
    }

    @Override
    public List<Listing> getByCityAndCategory(String cityId, String categoryId) {
        if(cityId == null || cityId.trim().isEmpty()) throw new ValidationException("City id cannot be empty");
        if(categoryId == null || categoryId.trim().isEmpty()) throw new ValidationException("Category id cannot be empty");
        return listingRepository.findByCityIdAndCategoryIdAndSatausOrderByCreatedAtDesc(cityId, categoryId, ListingStatus.ACTIVE);
    }

    @Override
    public UpdateListingResponse updateListing(String id, String token, UpdateListingRequest request) {
        Listing listing = listingRepository.findByIdAndEditToken(id, token).orElseThrow(() -> new ListingNotFoundException("Listing not found!"));
        listing.setTitle(request.getTitle());
        listing.setDescription(request.getDescription());
        listing.setPrice(request.getPrice());

        listingRepository.save(listing);

        return mapToUpdateListingResponse();
    }

    @Override
    public ListingResponse deleteListing(String id, String token) {
        Listing listing = listingRepository.findByIdAndEditToken(id, token).orElseThrow(() -> new ListingNotFoundException("Listing not found!"));
        listing.setStatus(ListingStatus.DELETED);
        listingRepository.save(listing);

        return mapToDeleteListingResponse();
    }

    @Override
    @Scheduled(fixedDelay = 24 * 60 * 60 * 1000)
    public void expireListing() {
        List<Listing> BoundToExpired = listingRepository.findByExpiresAtBeforeAndStatus(LocalDateTime.now(), ListingStatus.ACTIVE);

        for (Listing listing : BoundToExpired) {
            listing.setStatus(ListingStatus.EXPIRED);
            listingRepository.save(listing);
        }
    }

}
