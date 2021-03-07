package cn.edu.swu.utils;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @className: TermFilter
 * @author： yin
 * @date： 2021/3/6 16:02
 * @description：单词过滤工具类，该工具类的用处是过滤掉非 n、m、v 等其他词性的词
 */
public class TermFilter {
    private TermFilter() {
        stopwords = new HashSet<>();

        BufferedReader br = null;

        try {
            File stopwordsFile =  ResourceUtils.getFile("classpath:cd/sw.txt");
            br = new BufferedReader(new FileReader(stopwordsFile));

            String word = null;
            while((word = br.readLine()) != null){
                stopwords.add(word);
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

        System.err.println(stopwords);
    }

    private static final TermFilter INSTANCE = new TermFilter();

    public static TermFilter getInstance() {
        return INSTANCE;
    }

    private Set<String> stopwords;

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
            //首先进行第一层过滤，过滤掉非n、m、v词性的词
            if(term.nature.startsWith('n') || term.nature.startsWith('m') || term.nature.startsWith('v') || term.nature.startsWith('b'))
                if(filterFromStopwords(term))//如果停用词set中没有该词，通过
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
            //首先进行第一层过滤，过滤掉非n、m、v词性的词
            if(term.nature.startsWith('n') || term.nature.startsWith('m') || term.nature.startsWith('v') || term.nature.startsWith('b'))
                if(filterFromStopwords(term))//如果停用词set中没有该词，通过
                    result.add(term.word);
        }

        return result;
    }

    public boolean filterFromStopwords(Term term){
        return !stopwords.contains(term.word);
    }
}
