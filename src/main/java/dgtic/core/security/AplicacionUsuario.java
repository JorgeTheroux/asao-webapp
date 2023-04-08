package dgtic.core.security;

import dgtic.core.entity.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class AplicacionUsuario implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private final List<? extends GrantedAuthority> autoridad;
	private final String password;
	private final String username;
	private final boolean isAccountNonExpired;
	private final boolean isAccountNonLocked;
	private final boolean isCredencialsNonExprirex;
	private final boolean isEnabled;
	
	public AplicacionUsuario(Usuario usuario) {
		// TODO Auto-generated constructor stub
		this.username=usuario.getUsuario_usa();
		this.password=usuario.getClave();
		this.isEnabled=true;
		this.isAccountNonExpired=true;
		this.isAccountNonLocked=true;
		this.isCredencialsNonExprirex=true;
		List<GrantedAuthority> autoridades=new ArrayList<>();
		autoridades.add(new SimpleGrantedAuthority(usuario.getAutorizacion().getRol()));
		this.autoridad=autoridades;
	}
	
	public AplicacionUsuario(List<? extends GrantedAuthority> autoridad, String password, String username,
			boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredencialsNonExprirex,
			boolean isEnabled) {
		super();
		this.autoridad = autoridad;
		this.password = password;
		this.username = username;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredencialsNonExprirex = isCredencialsNonExprirex;
		this.isEnabled = isEnabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return autoridad;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return isCredencialsNonExprirex;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isEnabled;
	}

}
