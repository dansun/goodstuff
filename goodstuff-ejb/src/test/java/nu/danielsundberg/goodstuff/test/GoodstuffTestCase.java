package nu.danielsundberg.goodstuff.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class GoodstuffTestCase {

	protected final static Long NON_EXISTING_PLAYER_ID = -1L;
	protected final static Long PLAYER_ID = 1L;
	protected final static String PLAYER_NAME = "player_name";
	protected final static String PLAYER_PASSWORD = "player_password";
	protected final static String WRONG_PASSWORD = "other_password";
	protected final static Long GAME_ID = 1L;
	protected final static String GAME_NAME = "game_name";
	
	@BeforeClass
    public static void setUpTestCase() throws Exception {

    }
	
	@AfterClass
	public static void tearDownTestCase() throws Exception {
    
	}
	
}
