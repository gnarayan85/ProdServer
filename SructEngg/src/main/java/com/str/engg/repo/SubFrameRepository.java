package com.str.engg.repo;

import org.springframework.stereotype.Repository;

import com.str.engg.design.model.SubFrame;

import reactor.core.publisher.Mono;

@Repository
public interface SubFrameRepository {

	public Mono<Void> postSubFrame(Mono<SubFrame> subFrame);
}
