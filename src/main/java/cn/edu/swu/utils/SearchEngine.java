package cn.edu.swu.utils;

import cn.edu.swu.entity.Question;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.RAMDirectory;
import org.apache.poi.ss.formula.functions.T;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKQueryParser;
import org.wltea.analyzer.lucene.IKTokenizer;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchEngine {

    private static int RESULT_SIZE = 4;
    private RAMDirectory indexDirectory;
    private IndexWriter indexWriter;
    private IndexSearcher indexSearcher;

    private static SearchEngine INSTANCE = new SearchEngine();

    private SearchEngine() {
        try {
            this.startup();
            System.out.println("Search Engine startup.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SearchEngine getInstance(){
        return INSTANCE;
    }

    public void startup() throws IOException {
        // 创建索引存储空间
        this.indexDirectory = new RAMDirectory();

        // 初始化索引创建工具
        this.indexWriter = new IndexWriter(
                this.indexDirectory,
                new IKAnalyzer(),
                true,
                IndexWriter.MaxFieldLength.LIMITED
        );

        // 从QuestionHandler中读取需要检索的数据
        List<Question> questionList = QuestionsHandler.getQuestions();

        // 将数据写入到索引空间中
        for (Question question : questionList) {
            Document doc = new Document();
            Field id = new Field("id", question.getId().toString(), Field.Store.YES, Field.Index.ANALYZED);
            Field type = new Field("type", question.getType(), Field.Store.YES, Field.Index.ANALYZED);
            Field ask = new Field("ask", question.getQuestion(), Field.Store.YES, Field.Index.ANALYZED);
            Field keywords = new Field("keywords", question.getKeywordString(), Field.Store.YES, Field.Index.ANALYZED);
            Field answer = new Field("answer", question.getAnswer(), Field.Store.YES, Field.Index.ANALYZED);
            Field combine = new Field("combine", question.flat(), Field.Store.YES, Field.Index.ANALYZED);
            Field mediaType = new Field("media", question.getMediaType(), Field.Store.YES, Field.Index.NOT_ANALYZED);

            doc.add(id);
            doc.add(type);
            doc.add(ask);
            doc.add(keywords);
            doc.add(answer);
            doc.add(combine);
            doc.add(mediaType);

            this.indexWriter.addDocument(doc);
        }

        // 提交索引创建结果
        this.indexWriter.commit();
        //this.indexWriter.close();

        // 创建索引查询器
        this.indexSearcher = new IndexSearcher(this.indexDirectory, true);
    }

    public List<Question> search(String queryString) throws IOException {
        Document[] docs = this.doSearch(this.indexSearcher, queryString, new String[]{"ask", "keywords"}, RESULT_SIZE);
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < docs.length; i++) {
            questions.add(this.doc2Question(docs[i]));
        }

        System.err.println(indexSearcher.maxDoc());
        return questions;
    }

    public Document[] doSearch(IndexSearcher searcher, String queryString, String[] terms, int resultSize) throws IOException {
        TopScoreDocCollector collector = TopScoreDocCollector.create(resultSize, true);
        Query query = IKQueryParser.parseMultiField(terms, this.tokenize(queryString));
        searcher.search(query, collector);
        ScoreDoc[] hits = collector.topDocs(0, resultSize).scoreDocs;
        Document[] docs = new Document[hits.length];

        for (int i = 0; i < hits.length; i++) {
            docs[i] = searcher.doc(hits[i].doc);
        }
        return docs;
    }

    private String tokenize(String query_string) {
        StringBuffer sb = new StringBuffer();
        IKTokenizer tokenizer = new IKTokenizer(new StringReader(query_string), false);
        try {
            while (tokenizer.incrementToken()) {
                TermAttribute termAtt = tokenizer.getAttribute(TermAttribute.class);
                sb.append(termAtt.term()).append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private Question doc2Question(Document doc) {
        Question question = new Question();
        question.setId(Long.parseLong(doc.get("id")));
        question.setType(doc.get("type"));
        question.setQuestion(doc.get("ask"));
        question.setKeywords(Arrays.asList(doc.get("keywords").split(",")));
        question.setAnswer(doc.get("answer"));
        question.setMediaType(doc.get("media"));
        return question;
    }

    /**
     * @methodName：deleteDoc
     * @author: yin
     * @date: 2021/3/11  23:29
     * @param：Long id
     * @return：
     * @throws:
     * @description: 删除掉SearchEngine中的某个特定id的doc，如果没有这个doc，不做任何操作
     * lucene的更新接口有一点点奇怪，需要将所有内容删除后重新导入，直接调用 deleteDocuments 方法
     * 得到的结果不尽如人意
     */
    public void deleteDoc(Long id) throws Exception{

        List<Document> docs = new ArrayList<> ();

        for(int i = 0; i < indexSearcher.maxDoc(); i++){
            Document doc = indexSearcher.doc(i);

            if(!doc.get("id").equals(id.toString())){
                docs.add(doc);
            }
        }

        indexWriter.deleteAll();

        for (Document doc : docs) {
            indexWriter.addDocument(doc);
        }

        indexWriter.commit();

        indexSearcher = new IndexSearcher(indexDirectory,false);

        /*for (Document doc : docs) {
            indexWriter.addDocument(doc);
        }*/

        //System.err.println(indexSearcher.maxDoc());


        /*for(int i = 0; i < indexSearcher.maxDoc(); i++){
            if(indexSearcher.doc(i).get("id").equals(id.toString())){
                System.err.println(indexSearcher.doc(i));
            }
        }*/
    }

    public void updateOneDoc(Question question) throws Exception{
        List<Document> docs = new ArrayList<> ();

        for(int i = 0; i < indexSearcher.maxDoc(); i++){
            Document doc = indexSearcher.doc(i);


            System.out.println(doc.get("id"));
            if(doc.get("id").equals(question.getId().toString())){
                doc = new Document();

                Field id = new Field("id", question.getId().toString(), Field.Store.YES, Field.Index.ANALYZED);
                Field type = new Field("type", question.getType(), Field.Store.YES, Field.Index.ANALYZED);
                Field ask = new Field("ask", question.getQuestion(), Field.Store.YES, Field.Index.ANALYZED);
                Field keywords = new Field("keywords", question.getKeywordString(), Field.Store.YES, Field.Index.ANALYZED);
                Field answer = new Field("answer", question.getAnswer(), Field.Store.YES, Field.Index.ANALYZED);
                Field combine = new Field("combine", question.flat(), Field.Store.YES, Field.Index.ANALYZED);
                Field mediaType = new Field("media", question.getMediaType(), Field.Store.YES, Field.Index.NOT_ANALYZED);

                doc.add(id);
                doc.add(type);
                doc.add(ask);
                doc.add(keywords);
                doc.add(answer);
                doc.add(combine);
                doc.add(mediaType);
            }

            docs.add(doc);
        }

        indexWriter.deleteAll();

        for (Document doc : docs) {
            indexWriter.addDocument(doc);
        }

        indexWriter.commit();

        indexSearcher = new IndexSearcher(indexDirectory,false);

    }

    public void addDoc(Question question) throws IOException {
        Document doc = new Document();

        try {
            Field id = new Field("id", question.getId().toString(), Field.Store.YES, Field.Index.ANALYZED);
            Field type = new Field("type", question.getType(), Field.Store.YES, Field.Index.ANALYZED);
            Field ask = new Field("ask", question.getQuestion(), Field.Store.YES, Field.Index.ANALYZED);
            Field keywords = new Field("keywords", question.getKeywordString(), Field.Store.YES, Field.Index.ANALYZED);
            Field answer = new Field("answer", question.getAnswer(), Field.Store.YES, Field.Index.ANALYZED);
            Field combine = new Field("combine", question.flat(), Field.Store.YES, Field.Index.ANALYZED);
            Field mediaType = new Field("media", question.getMediaType(), Field.Store.YES, Field.Index.NOT_ANALYZED);

            doc.add(id);
            doc.add(type);
            doc.add(ask);
            doc.add(keywords);
            doc.add(answer);
            doc.add(combine);
            doc.add(mediaType);

            indexWriter.addDocument(doc);
        }catch (NullPointerException npe) {
            System.err.println("某一属性的值为空！！请注意检查");
            npe.printStackTrace();
        }



        indexWriter.commit();

        indexSearcher = new IndexSearcher(indexDirectory,false);
    }
}