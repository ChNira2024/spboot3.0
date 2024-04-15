package com.karapada.school.adminstrative.security.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karapada.school.adminstrative.security.entity.KarapadaSchoolAdminInfo;

public interface IKarapadaSchoolAdminInfoRepository extends JpaRepository<KarapadaSchoolAdminInfo, Long> {
    Optional<KarapadaSchoolAdminInfo> findAdminInfoByName(String username);

}