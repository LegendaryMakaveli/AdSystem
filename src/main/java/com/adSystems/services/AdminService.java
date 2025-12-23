package com.adSystems.services;

public interface AdminService {
    String upgradeUserToPremium(String userId);
    String downgradeUser(String userId);
}
