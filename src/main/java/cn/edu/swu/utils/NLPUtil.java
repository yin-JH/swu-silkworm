package cn.edu.swu.utils;

import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

/**
 * @className: NLPUtil
 * @author： yin
 * @date： 2021/3/6 15:48
 * @description：NLP切分工具，引用自 hanlp 工具包，编写为一个饿汉式单例
 */
public class NLPUtil {
    private NLPUtil() {

    }

    private static final NLPUtil INSTANCE = new NLPUtil();

    public static NLPUtil getInstance(){
        return INSTANCE;
    }

    public List<Term> segOneQuestion(String question){
        Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);

        List<Term> keywords = nShortSegment.seg(question);

        return keywords;
    }
}
