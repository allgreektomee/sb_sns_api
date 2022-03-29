package com.devcation.sns.test;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class _ResponseDTO<T> {
    private String resultCode;
    private String resultMsg;
    private List<T> list;
}

