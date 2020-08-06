package com.example.dockerdemo;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/5/7 17:06
 */
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Ping4jTest {
    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
        String str = "这里为什么用  字符this is 4 串数组接受返回的拼音呢";
        // 这里为什么用字符串数组接受返回的拼音呢，  因为中文有多音字，输入“长”，会返回zhang3和chang2,（2,3表示第几声)
        String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(str.charAt(0)); // str.charAt(0) 第一个汉字
        for (String py : pinyin) {
            System.out.println(py);
        }
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String result = PinyinHelper.toHanYuPinyinString(str.replace(" ",""), hanyuPinyinOutputFormat, "-", true);
        System.out.println(result);
    }
}
