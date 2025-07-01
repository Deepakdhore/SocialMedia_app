package com.socialMedia.socialMediaApp.repositories;

import com.socialMedia.socialMediaApp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    Optional<Like> findByUser_IdAndPost_Id(Long userId, Long PostId);

    boolean existsByUser_IdAndPost_Id(Long userId, Long postId);
}
