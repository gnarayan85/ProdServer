package com.str.engg.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.str.engg.design.model.SubFrame;
import com.str.engg.repo.ReactiveStructEnggSubFrameRepository;
import com.str.engg.repo.SubFrameRepository;

import reactor.core.publisher.Mono;

@Repository
public class StructEnggSubFrameRepositoryImpl implements SubFrameRepository{
	
	@Autowired
	private final ReactiveStructEnggSubFrameRepository reactiveStructEnggSubFrameRepository;
	
	
	public StructEnggSubFrameRepositoryImpl(ReactiveStructEnggSubFrameRepository reactiveStructEnggSubFrameRepository) {
		this.reactiveStructEnggSubFrameRepository = reactiveStructEnggSubFrameRepository;
	}

	@Override
	public Mono<Void> postSubFrame(Mono<SubFrame> subFrame) {
		Mono<SubFrame> projecthMono =  subFrame.doOnNext(SubFrame -> {
			reactiveStructEnggSubFrameRepository.insert(SubFrame).subscribe();
            System.out.println("########### POST:" + SubFrame);
        });
		
		return projecthMono.then();
	}
	
}
