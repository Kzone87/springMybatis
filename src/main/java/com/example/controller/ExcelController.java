package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.SearchVO;
import com.example.Model.userVO;
import com.example.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@RestController
@RequestMapping("/api/users")
public class ExcelController {
    @Autowired
    private UserService userService;

    @GetMapping("/export")
    public void exportToExcel(SearchVO searchVO, HttpServletResponse response) throws IOException {
        // 검색 결과 가져오기
        List<userVO> users = userService.searchUsers(searchVO);

        // 엑셀 파일 생성
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");
        
        // 헤더 생성
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Email");

        // 데이터 추가
        int rowNum = 1;
        for (userVO user : users) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getEmail());
        }

        // 응답 설정
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");
        
        // 엑셀 파일 작성
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}

