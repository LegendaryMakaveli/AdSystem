package com.adSystems.services;

import com.adSystems.datas.models.Listing;
import com.adSystems.datas.models.ListingStatus;
import com.adSystems.datas.repositories.ListingRepository;
import com.adSystems.dtos.reponses.ListingResponse;
import com.adSystems.dtos.reponses.UpdateListingResponse;
import com.adSystems.dtos.requests.GetByCityAndCategoryRequest;
import com.adSystems.dtos.requests.ListingRequests;
import com.adSystems.dtos.requests.UpdateListingRequest;
import com.adSystems.exception.ListingNotFoundException;
import com.adSystems.exception.ValidationException;
import com.adSystems.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.adSystems.util.Mapper.mapToDeleteListingResponse;
import static com.adSystems.util.Mapper.mapToUpdateListingResponse;
import static com.adSystems.validation.Validations.validateCreateListing;


@AllArgsConstructor
@Service
public class ListServicesImplementation implements ListingServices{
    @Autowired
    private ListingRepository listingRepository;

    @Override
    public ListingResponse createListing(ListingRequests request) {
        validateCreateListing(request);
        Listing listing = Mapper.mapToCreateAListing(request);
        listingRepository.save(listing);
        return Mapper.mapToCreateListingResponse();
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
    public List<Listing> getByCityAndCategory(GetByCityAndCategoryRequest request) {
        if(request.getCityId() == null || request.getCityId().trim().isEmpty()) throw new ValidationException("City id cannot be empty");
        if(request.getCategoryId() == null || request.getCategoryId().trim().isEmpty()) throw new ValidationException("Category id cannot be empty");
        return listingRepository.findByCityIdAndCategoryIdAndSatausOrderByCreatedAtDesc(request.getCityId(), request.getCategoryId(), ListingStatus.ACTIVE);
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
    public void expireListing() {
        List<Listing> BoundToExpired = listingRepository.findByExpiresAtBeforeAndStatus(LocalDateTime.now(), ListingStatus.ACTIVE);

        for (Listing listing : BoundToExpired) {
            listing.setStatus(ListingStatus.EXPIRED);
            listingRepository.save(listing);
        }
    }

}
