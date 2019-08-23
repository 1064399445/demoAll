package cn.jun.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.util.Date;

public class SolrJDemo {

    public static void main(String[] args) throws Exception{
        //构建solr客户端
        HttpSolrClient.Builder builder = new HttpSolrClient.Builder("http://192.168.81.135:8983/solr/my_core");
        SolrClient client = builder.build();
        //向solr添加索引，如果有索引则为更新操作
        client.addBean(new User("123","诸葛亮","17","男",new Date(),"神算子"));
        //commit后才生效
        client.commit();

        //删除索引
        client.deleteById("123");
        client.commit();

        //封装查询对象
        SolrQuery query = new SolrQuery("*:*");
        query.setFields("name","age","sex","birth","description");
        //执行查询并返回response对象
        QueryResponse response = client.query(query);
        SolrDocumentList list = response.getResults();
        //遍历结果并输出
        for(SolrDocument document : list){
            String name = (String) document.getFirstValue("name");
            String age = (String) document.getFirstValue("age");
            String sex = (String) document.getFirstValue("sex");
            Date birth = (Date) document.getFirstValue("birth");
            String description = (String) document.getFirstValue("description");
            User user = new User(name,age,sex,birth,description);
            System.out.println(user);
        }



    }
}
