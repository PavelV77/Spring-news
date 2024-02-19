package com.example.Nnnews.repositories;

import com.example.Nnnews.entities.Like;
import com.example.Nnnews.types.entityfieldtypes.ActivityTypeForLikes;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface LikeRepository extends CrudRepository<Like, UUID>, BaseRepository<Like>, JpaSpecificationExecutor<Like> {
    //@Query(value = "SELECT count(id) FROM Like WHERE((newsId = nnn) and (type_Of_Activity = ttt::newsschema.activity_type_for_likes))", nativeQuery = true)
    @Query(value = "SELECT count(*) FROM newsschema.like l WHERE( l.news_id = :findId and l.type_of_activity = cast(:type as newsschema.activity_type_for_likes))", nativeQuery = true)
    long countByNewsIdAndTypeOfActivity(@Param("findId") UUID id, @Param("type") String typeOfActivity);
}
