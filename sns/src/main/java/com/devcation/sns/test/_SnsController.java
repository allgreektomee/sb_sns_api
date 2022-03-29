package com.devcation.sns.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("_sns")
public class _SnsController {

    @GetMapping("/{id}")
    public String snsWithPathVariables(@PathVariable(required = false) int id) {
        return "PathVariable sid = " + id;
    }

    @GetMapping("/snsRP")
    public String snsWithRequestParam(@RequestParam(required = false) int sid) {
        return "RequestParam sid = " + sid;
    }

    @GetMapping("/snsRB")
    public String snsWithRequestBody(@RequestBody _SnsDTO snsDTO) {
        return "snsDTO " +snsDTO.getSid()+" // " + snsDTO.getTitle() +" // " + snsDTO.getContent();
    }

    @GetMapping("/resBody")
    public _ResponseDTO<String> responseBody() {
        List<String> list = new ArrayList<>();
        list.add("ResBody");
        _ResponseDTO<String> res = _ResponseDTO.<String>builder().list(list).resultCode("0000").resultMsg("Completed").build();

        return res;
    }

    @GetMapping("/snsResBody")
    public _ResponseDTO<_SnsDTO> snsWithResponseBody() {
        _SnsDTO snsDTO1 = _SnsDTO.builder().content("내용1").title("제목1").date("10분전").img("000.png").build();
        _SnsDTO snsDTO2 = _SnsDTO.builder().content("내용2").title("제목2").date("20분전").img("111.png").build();

        List<_SnsDTO> list = new ArrayList<>();
        list.add(snsDTO1);
        list.add(snsDTO2);
        _ResponseDTO<_SnsDTO> res = _ResponseDTO.<_SnsDTO>builder().list(list).resultCode("0000").resultMsg("Completed").build();

        return res;
    }

    @GetMapping("/ResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity() {
        List<String> list = new ArrayList<>();
        list.add(" 400");

        _ResponseDTO<String> response = _ResponseDTO.<String>builder().list(list).build();

        return ResponseEntity.badRequest().body(response); // 400 error
    }

}
