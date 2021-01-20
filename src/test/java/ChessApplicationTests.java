import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.kb.chess.server.ChessApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ChessApplication.class)
class ChessApplicationTests {

	@Test
	void contextLoads() {
	}

}
