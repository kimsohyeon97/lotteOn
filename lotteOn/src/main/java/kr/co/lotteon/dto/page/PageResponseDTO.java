package kr.co.lotteon.dto.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDTO<T> implements Serializable {

    private List<T> dtoList;

    private int subCateNo;
    private String sortType;
    private String period;
    private String view;

    private int pg;
    private int size;
    private int total;
    private int startNo;
    private int start, end;
    private boolean prev, next;

    private String searchType;
    private String keyword;

    private String startDate;
    private String endDate;

    private long pendingCount;

    private double avgRate;

    @Builder
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<T> dtoList, int total) {
        this.subCateNo = pageRequestDTO.getSubCateNo();
        this.pg = pageRequestDTO.getPg();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.searchType = pageRequestDTO.getSearchType();
        this.keyword = pageRequestDTO.getKeyword();

        this.startDate = pageRequestDTO.getStartDate();
        this.endDate = pageRequestDTO.getEndDate();

        this.startNo = total - ((pg - 1) * size);

        int blockSize = 10; // 원하는 블록 크기
        this.end = (int) (Math.ceil(this.pg / (double) blockSize)) * blockSize;
        this.start = this.end - (blockSize - 1);

        int last = (int) (Math.ceil(total / (double) size));
        this.end = end > last ? last : end;

        this.prev = this.start > 1;
        this.next = total > this.end * this.size;


    }


    public int size() {
        return dtoList != null ? dtoList.size() : 0;
    }


}
