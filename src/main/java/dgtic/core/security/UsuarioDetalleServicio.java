package dgtic.core.security;

import dgtic.core.entity.Usuario;
import dgtic.core.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class UsuarioDetalleServicio implements UserDetailsService {

	@Autowired
	private IUsuarioService iUsuarioService;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario us = iUsuarioService.buscarUsuario(username);
		return new AplicacionUsuario(us);

		
	}

	private User userBuilder(String username, String password, String... roles) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> autoridades = new ArrayList<>();
		for (String role : roles) {
			autoridades.add(new SimpleGrantedAuthority(role));
		}
		return new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				autoridades);
	}

	/*
	 * @Bean public DataSource dataSource() { return
	 * DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver").
	 * username("root").password("1234")
	 * .url("jdbc:mysql://localhost:3306/dgtic?useSSL=false&serverTimezone=UTC").
	 * build(); }
	 */
}
