package com.systems.backend.mapper;

import com.systems.backend.responses.AccountResponse;
import com.systems.backend.responses.HistoryDownloadResponse;
import com.systems.backend.model.Account;
import com.systems.backend.model.HistoryDownload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface HistoryDownloadMapper {
    @Mapping(source = "historyDownloadId.account.username", target = "username")
    @Mapping(source = "historyDownloadId.document.title", target = "documentName")
    HistoryDownloadResponse toDTO(HistoryDownload historyDownload);

    default Page<HistoryDownloadResponse> toDTOPage(Page<HistoryDownload> historyDownloads) {
        return historyDownloads.map(this::toDTO);
    }
}
