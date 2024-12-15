package com.cookyuu.morning_routine.domain.member.entity;

import com.cookyuu.morning_routine.domain.region.entity.RegionInterest;
import com.cookyuu.morning_routine.domain.stock.entity.StockInterest;
import com.cookyuu.morning_routine.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.ArrayList;
import java.util.List;

@Entity

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MR_MEMBER")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "login_id", nullable = false)
    private String loginId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "is_connect_oauth", nullable = false)
    private boolean isConnectOauth;

    @Enumerated(EnumType.STRING)
    @Column(name = "oauth_type", nullable = true)
    private OauthType oauthType;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    @ColumnDefault("'USER'")
    private RoleType role;

    @OneToMany(mappedBy = "member")
    private List<RegionInterest> regionInterests = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<StockInterest> stockInterests = new ArrayList<>();
}
