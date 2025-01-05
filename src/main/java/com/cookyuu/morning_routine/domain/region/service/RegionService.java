package com.cookyuu.morning_routine.domain.region.service;

import com.cookyuu.morning_routine.domain.region.dto.RegisterRegionFromFileDto;
import com.cookyuu.morning_routine.domain.region.entity.Region;
import com.cookyuu.morning_routine.domain.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegionService {

    @Value("${collector.region.path}")
    private String filePath;

    private final RegionRepository regionRepository;

    public void registerFromFile() throws IOException {
        List<RegisterRegionFromFileDto.FileData> fileDataList = getResisterFileDataList();
        if (fileDataList == null) {
            return ;
        }
        List<Region> regionList = convertFromFileDataList(fileDataList);
        registerRegionData(regionList);
    }

    private List<Region> convertFromFileDataList(List<RegisterRegionFromFileDto.FileData> fileDataList) {
        List<Region> regionList = new ArrayList<>();
        for (RegisterRegionFromFileDto.FileData data : fileDataList) {
            regionList.add(data.toEntity());
        }
        return regionList;
    }

    @Transactional
    public void registerRegionData(List<Region> regionList) {
        regionRepository.saveAll(regionList);
    }

    public List<RegisterRegionFromFileDto.FileData> getResisterFileDataList() throws IOException {
        ZipSecureFile.setMinInflateRatio(0.001);
        List<RegisterRegionFromFileDto.FileData> fileDataList = new ArrayList<>();
        Resource resource = new ClassPathResource(filePath);
        InputStream is = resource.getInputStream();
        XSSFWorkbook registerWorkBook = new XSSFWorkbook(is);

        int sheetCount = registerWorkBook.getNumberOfSheets();
        if (sheetCount < 1) {
            log.error("[Region::Register] Excel sheet is empty. ");
            return null;
        }
        XSSFSheet worksheet = registerWorkBook.getSheetAt(0);

        for (int i=1; i<worksheet.getPhysicalNumberOfRows(); i++) {
            DataFormatter formatter = new DataFormatter();
            XSSFRow row = worksheet.getRow(i);

            String classification = formatter.formatCellValue(row.getCell(0));
            String code = formatter.formatCellValue(row.getCell(1));
            String firstRegion = formatter.formatCellValue(row.getCell(2));
            String secondRegion = formatter.formatCellValue(row.getCell(3));
            String thirdRegion = formatter.formatCellValue(row.getCell(4));
            int gridX = Integer.parseInt(formatter.formatCellValue(row.getCell(5)));
            int gridY = Integer.parseInt(formatter.formatCellValue(row.getCell(6)));
            int longitudeHour = Integer.parseInt(formatter.formatCellValue(row.getCell(7)));
            int longitudeMin = Integer.parseInt(formatter.formatCellValue(row.getCell(8)));
            float longitudeSec = Float.parseFloat(formatter.formatCellValue(row.getCell(9)));
            int latitudeHour = Integer.parseInt(formatter.formatCellValue(row.getCell(10)));
            int latitudeMin = Integer.parseInt(formatter.formatCellValue(row.getCell(11)));
            float latitudeSec = Float.parseFloat(formatter.formatCellValue(row.getCell(12)));

            fileDataList.add(RegisterRegionFromFileDto.FileData.builder()
                    .classification(classification)
                    .code(code)
                    .firstRegion(firstRegion)
                    .secondRegion(secondRegion)
                    .thirdRegion(thirdRegion)
                    .gridX(gridX)
                    .gridY(gridY)
                    .longitudeHour(longitudeHour)
                    .longitudeMin(longitudeMin)
                    .longitudeSec(longitudeSec)
                    .latitudeHour(latitudeHour)
                    .latitudeMin(latitudeMin)
                    .latitudeSec(latitudeSec)
                    .build()
            );
        }
        log.info("[Region] Get registration file data list complete. count : {}", fileDataList.size());
        return fileDataList;
    }

}
