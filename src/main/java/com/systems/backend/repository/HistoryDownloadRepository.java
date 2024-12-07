package com.systems.backend.repository;

import com.systems.backend.model.Account;
import com.systems.backend.model.HistoryDownload;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryDownloadRepository extends JpaRepository<HistoryDownload, HistoryDownload.HistoryDownloadId> {
    @Query("SELECT h FROM HistoryDownload h WHERE h.historyDownloadId.account = :accountId")
    List<HistoryDownload> findByAccountId(@Param("accountId") Long accountId);

    @Query("SELECT h from HistoryDownload h WHERE h.historyDownloadId.document = :documentId")
    List<HistoryDownload> findByDocumentId(@Param("documentId") Long documentId);
}
