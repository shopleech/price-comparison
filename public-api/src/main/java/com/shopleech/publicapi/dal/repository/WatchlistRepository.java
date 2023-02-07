package com.shopleech.publicapi.dal.repository;

import com.shopleech.publicapi.domain.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ahto Jalak
 * @since 06.02.2023
 */
@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Integer>, WatchlistRepositoryCustom {

//   void add(Watchlist watchlist);
}
