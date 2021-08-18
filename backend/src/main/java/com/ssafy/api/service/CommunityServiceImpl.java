package com.ssafy.api.service;

import com.ssafy.db.entity.Community;
import com.ssafy.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 리뷰 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("CommunityService")
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    CommunityService communityService;

    @Autowired
    UserService userService;

    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    ConferenceCategoryRepository conferenceCategoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommunityRepositorySupport communityRepositorySupport;

    @Override
    public Community createPost(String descript, String title, String userId, Long conferenceCategoryid) {
        Community community = new Community();
        community.setConferenceCategory(conferenceCategoryRepository.findById(conferenceCategoryid).get());
        community.setUser(userService.getUserByUserId(userId));
        community.setDescript(descript);
        community.setTitle(title);
        return communityRepository.save(community);
    }

    @Override
    public Optional<List<Community>> getPosts(Long conferenceCategoryid) {
        Optional<List<Community>> communities = communityRepositorySupport.findReviewByConferenceCategoryId(conferenceCategoryid);
        return communities;
    }
}
