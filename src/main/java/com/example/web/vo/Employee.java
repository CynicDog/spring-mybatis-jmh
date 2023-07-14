package com.example.web.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data @Alias("Employee")
@NoArgsConstructor
public class Employee implements UserDetails {

    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private Job job;
    private Double salary;
    private Double commissionPct;
    private Employee manager;
    private Department department;

    public Employee(int id) {
        this.id = id;
    }

    public Employee(String firstName, String lastName, String password, String email, String phoneNumber, Date hireDate, Double salary, Double commissionPct) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.salary = salary;
        this.commissionPct = commissionPct;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER_ROLE"));
    }

    @Override
    public String getUsername() {
        return this.firstName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
