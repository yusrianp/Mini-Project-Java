package com.example.med_id.repositories;

import com.example.med_id.model.BiodataAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BiodataAddressRepo extends JpaRepository<BiodataAddress, Long> {
    @Query("FROM BiodataAddress p INNER JOIN Location e ON e.Id = p.LocationId " +
            "WHERE p.isDelete = false AND p.BiodataId = ?1 AND " +
            "((lower(p.Address) LIKE lower(concat('%',?2,'%')) " +
            "OR lower(p.Recipient) LIKE lower(concat('%',?2,'%')) OR lower(p.Label) LIKE lower(concat('%',?2,'%'))" +
            "OR lower(e.Name) LIKE lower(concat('%',?2,'%')))) ")
    Page<BiodataAddress> SearchBiodataAddress(Long id, String keyword, Pageable pageable);

    @Query("FROM BiodataAddress WHERE isDelete = false and BiodataId = ?1")
    List<BiodataAddress> GetAvailable(Long id);

    @Query(" SELECT f FROM BiodataAddress f WHERE isDelete = false and BiodataId = ?1 ORDER BY Label")
    Page<BiodataAddress> GetAvailablePage(Long id, Pageable pageable);

    @Query(" SELECT f FROM BiodataAddress f WHERE isDelete = false and BiodataId = ?1 ORDER BY Recipient")
    Page<BiodataAddress> GetAvailablePageRecipient(Long id, Pageable pageable);

    @Query(" SELECT f FROM BiodataAddress f WHERE isDelete = false and BiodataId = ?1 ORDER BY Address")
    Page<BiodataAddress> GetAvailablePageAddress(Long id, Pageable pageable);

    @Query(" SELECT f FROM BiodataAddress f WHERE isDelete = false and BiodataId = ?1 ORDER BY Label DESC")
    Page<BiodataAddress> GetAvailablePageDsc(Long id, Pageable pageable);

    @Query(" SELECT f FROM BiodataAddress f WHERE isDelete = false and BiodataId = ?1 ORDER BY Recipient DESC")
    Page<BiodataAddress> GetAvailablePageRecipientDsc(Long id, Pageable pageable);

    @Query(" SELECT f FROM BiodataAddress f WHERE isDelete = false and BiodataId = ?1 ORDER BY Address DESC")
    Page<BiodataAddress> GetAvailablePageAddressDsc(Long id, Pageable pageable);
}
