package cn.edu.swu.utils;

import com.hankcs.hanlp.seg.common.Term;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: TermFilter
 * @author： yin
 * @date： 2021/3/6 16:02
 * @description：单词过滤工具类，该工具类的用处是过滤掉非 n、m、v 等其他词性的词
 */
public class TermFilter {
    private TermFilter() {

    }

    private static final TermFilter INSTANCE = new TermFilter();

    public static TermFilter getInstance() {
        return INSTANCE;
    }

    /**
     * @methodName：filter
     * @author: yin
     * @date: 2021/3/6  22:24
     * @param：List<Term> terms
     * @return：List<Term>
     * @throws:
     * @description: 过滤掉所有的非 n、m、v、b 的单词，并且将过滤完毕的Term组成一个List返回
     */
    public List<Term> filter(List<Term> terms){
        List<Term> result = new ArrayList<>();

        for (Term term : terms) {
            if(term.nature.startsWith('n') || term.nature.startsWith('m') || term.nature.startsWith('v') || term.nature.startsWith('b'))
                result.add(term);
        }

        return result;
    }

    /**
     * @methodName：filterAndReturnStr
     * @author: yin
     * @date: 2021/3/6  22:24
     * @param：List<Term> terms
     * @return：List<String>
     * @throws:
     * @description: 过滤掉所有的非 n、m、v、b 的单词，并且将过滤完毕的Term中的word组成一个List返回
     * 这个方法和 filter 方法的唯一区别就在于 filter 直接返回过滤完毕的 term List，filterAndReturnStr
     * 方法会将term中的word取出来，再将之组成一个list然后返回
     */
    public List<String> filterAndReturnStr(List<Term> terms){
        List<String> result = new ArrayList<>();

        for (Term term : terms) {
            if(term.nature.startsWith('n') || term.nature.startsWith('m') || term.nature.startsWith('v') || term.nature.startsWith('b'))
                result.add(term.word);
        }

        return result;
    }
}
