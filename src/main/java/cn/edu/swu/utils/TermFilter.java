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

    public List<Term> filter(List<Term> terms){
        List<Term> result = new ArrayList<>();

        for (Term term : terms) {
            if(term.nature.startsWith('n') || term.nature.startsWith('m') || term.nature.startsWith('v') || term.nature.startsWith('b'))
                result.add(term);
        }

        return result;
    }
}
