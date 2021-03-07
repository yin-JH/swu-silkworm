package cn.edu.swu.utils;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

/**
 * @className: NLPUtil
 * @author： yin
 * @date： 2021/3/6 15:48
 * @description：NLP切分工具，引用自 hanlp 工具包，编写为一个饿汉式单例
 */
public class NLPUtil {
    private NLPUtil() {
        BufferedReader br = null;
        try {
            File customDirectoryFile =  ResourceUtils.getFile("classpath:cd/cd.txt");
            br = new BufferedReader(new FileReader(customDirectoryFile));

            String word = null;
            while((word = br.readLine()) != null){
                System.out.println(word);
                CustomDictionary.add(word);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static final NLPUtil INSTANCE = new NLPUtil();

    public static NLPUtil getInstance(){
        return INSTANCE;
    }

    public List<Term> segOneQuestion(String question){
        Segment nShortSegment = new NShortSegment().enableCustomDictionary(true).enablePlaceRecognize(true).enableOrganizationRecognize(true);

        List<Term> keywords = nShortSegment.seg(question);

        return keywords;
    }
}
