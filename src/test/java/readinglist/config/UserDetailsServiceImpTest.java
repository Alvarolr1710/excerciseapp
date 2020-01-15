package readinglist.config;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import readinglist.repository.ReaderRepository;
import readinglist.utils.Reader;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImpTest {

    private final Reader mockReader;
    private final String username;
    @InjectMocks
    private UserDetailsServiceImp userDetailsServiceImp;

    @Mock
    private ReaderRepository readerRepository;


    public UserDetailsServiceImpTest() {
        userDetailsServiceImp = new UserDetailsServiceImp();
        username = "Alvaro";
        mockReader = createMockReader(username);
    }

    @Test
    public void shouldLoadUserByUsername() {
        Mockito.when(readerRepository.findByusername(username)).thenReturn(mockReader);
        UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(username);
        assertThat(userDetails, is(IsNull.notNullValue()));
        assertThat(userDetails.getUsername(), is(username));
        assertThat(userDetails.getPassword(), instanceOf(String.class));

    }

    private Reader createMockReader(String username) {
        Reader reader = new Reader();
        reader.setUsername(username);
        reader.setFullname(username);
        reader.setPassword("password");
        return reader;
    }


}
