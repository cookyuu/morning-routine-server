package com.cookyuu.morning_routine.domain.weather.entity;

import com.cookyuu.morning_routine.domain.region.entity.Region;
import com.cookyuu.morning_routine.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MR_WEATHER")
public class Weather extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_id")
    private Long id;
    @Column(name = "base_date")
    private String baseDate;
    @Column(name = "base_time")
    private String baseTime;

    @Column(name = "tmp_val")
    private String tmp;
    @Column(name = "uuu_val")
    private String uuu;
    @Column(name = "vvv_val")
    private String vvv;
    @Column(name = "vec_val")
    private String vec;
    @Column(name = "wsd_val")
    private String wsd;
    @Column(name = "sky_val")
    private String sky;
    @Column(name = "pty_val")
    private String pty;
    @Column(name = "pop_val")
    private String pop;
    @Column(name = "wav_val")
    private String wav;
    @Column(name = "pcp_val")
    private String pcp;
    @Column(name = "reh_val")
    private String reh;
    @Column(name = "sno_val")
    private String sno;
    @Column(name = "tmx_val")
    private String tmx;
    @Column(name = "tmn_val")
    private String tmn;

    @ManyToOne
    @JoinColumn(name = "region_code")
    private Region region;
}