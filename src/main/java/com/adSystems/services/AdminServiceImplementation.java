package com.adSystems.services;

import com.adSystems.datas.models.SubscriptionPlan;
import com.adSystems.datas.models.User;
import com.adSystems.datas.repositories.UserRepository;
import com.adSystems.exception.UserNotFoundException;
import com.adSystems.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class AdminServiceImplementation implements AdminService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public String upgradeUserToPremium(String userId) {
        if(userId == null || userId.trim().isEmpty())throw new ValidationException("User ID cannot be empty");
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setSubscriptionPlan(SubscriptionPlan.PREMIUM);
        userRepository.save(user);

        return "User upgraded successfully";
    }

    @Override
    public String downgradeUser(String userId) {
        return "";
    }
}
