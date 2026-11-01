package org.txf.rag;

import com.alibaba.cloud.ai.reader.mysql.MySQLDocumentReader;
import com.alibaba.cloud.ai.reader.mysql.MySQLResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MysqlDocumentReaderTest {


    private MySQLResource mysqlResource;

    private MySQLDocumentReader reader;

    @BeforeEach
    void setUp() {

        List<String> contentColumn = List.of("id", "username", "password", "name");
        List<String> metadataColumn = List.of("id");
        // Setup test MySQL resource
        mysqlResource = new MySQLResource("localhost", 3306, "xm_job", "root", "123456", "select * from user", contentColumn, metadataColumn);

        reader = new MySQLDocumentReader(mysqlResource);
    }

    @Test
    void testGetDocuments() {
        // This test requires a running MySQL instance with test data
        // You may need to modify the connection details and query using system
        // properties:
        // -Dmysql.host=your_host -Dmysql.port=your_port -Dmysql.database=your_db
        // -Dmysql.username=your_user -Dmysql.password=your_pass

        List<Document> documents = reader.get();

        // 把获取到的Document打印出来
        documents.forEach(System.out::println);
    }
}
