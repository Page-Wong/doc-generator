package com.bshhr.docgenerator.utils.poi;

import lombok.var;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ExcelUtilTest {
    @Test
    public void testReadExcel(){
        File file = new File("D:\\下载\\batchWordDemo\\准考证模板_考生名单.xls");
        var maps = ExcelUtil.readExcel(file);
        Assertions.assertNotNull(maps);
    }
}