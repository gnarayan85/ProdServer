package com.str.engg.design.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.str.engg.design.model.AnalysisStage;
import com.str.engg.design.model.Beam;
import com.str.engg.design.model.BeamAtStage;
import com.str.engg.design.model.BeamResultData;
import com.str.engg.design.model.BeamSection;
import com.str.engg.design.model.BeamSectionPropertyAtStage;
import com.str.engg.design.model.EndForces;
import com.str.engg.design.model.LoadCase;
import com.str.engg.design.model.Project;
import com.str.engg.design.model.SectionPhase;
import com.str.engg.design.model.SubFrame;
import com.str.engg.design.model.UDL;
import com.str.engg.fem.loads.Fem_UDL;
import com.str.engg.fem.loads.LoadGroup;
import com.str.engg.fem.structure.Element;
import com.str.engg.fem.structure.Node;
import com.str.engg.fem.structure.Structure;
import com.str.engg.shape.CompositeShape;
import com.str.engg.shape.InvertedTee;
import com.str.engg.shape.Rectangle;
import com.str.engg.shape.Shape;

@Service
public class AnalysisService {

	@Autowired
	Calculate calculate;
	Node startNode;
	Element beamElement;
	int count = 0;
	int index;
	int counter;

	Shape currentShape = null;
	Shape previousShape = null;

	public Project analyse(Project project) {

		List<BeamAtStage> beamAtStageList = new ArrayList<BeamAtStage>();

		SubFrame subFrame = project.getSubframeList().get(0);

		project.getAnalysisStagesList().stream().forEach((AnalysisStage stage) -> {
			System.out.println("inside stage- " + stage.getName());

			if (stage.isContinuous()) {
				System.out.println("is continouous");
				getFiniteAnalysis(stage, subFrame);

			}

			subFrame.getBeamList().stream().forEach((Beam beam) -> {

				BeamAtStage beamAtstage = new BeamAtStage();
				beamAtstage.setStageId(stage.getStageId());
				beamAtstage.setBeamId(beam.getBeamId());
				beamAtstage.setSpan(beam.getSpan());
				beamAtstage.setSupportOrdinates(stage.getSupportOrdinates());
				Map<Integer, List<BeamResultData>> beamResultMap = new HashMap();
				beamAtstage.setBeamResultMap(beamResultMap);
				if (stage.getLoadCaseList() != null && !stage.getLoadCaseList().isEmpty()) {
					stage.getLoadCaseList().stream().forEach((LoadCase loadcase) -> {
						List<BeamResultData> beamResultDataList = new ArrayList();

						for (double i = 0; i <= 1; i = i + 0.10) {
							BeamResultData result = new BeamResultData();
							result.setX(i);
							if (loadcase.getUdlList() != null && !loadcase.getUdlList().isEmpty()) {
								double span = beam.getSpan();

								loadcase.getUdlList().stream().forEach((UDL udl) -> {

									if (udl.getBeamIdList().contains(beam.getBeamId())) {
										double moment = calculate.getUdlMoment(result.getMoment(), udl.getValue(),
												result.getX(), span);
										result.setMoment(moment);
										double shear = calculate.getUdlShear(result.getShear(), udl.getValue(),
												result.getX(), span);
										result.setShear(shear);
									}
									beamResultDataList.add(result);
								});
							}

						}
						Map<Integer, List<BeamResultData>> map = beamAtstage.getBeamResultMap();
						map.put(loadcase.getLoadCaseId(), beamResultDataList);
						beamAtstage.setBeamResultMap(map);
					});
				}
				beamAtStageList.add(beamAtstage);
			});

		});

		System.out.println(beamAtStageList.size());
		subFrame.setBeamAtStageList(beamAtStageList);
		return project;

	}

	// end**********************************************

	private void getFiniteAnalysis(AnalysisStage stage, SubFrame subFrame) {
		System.out.println("inside");

		ArrayList<Integer> beamElementIndexes = new ArrayList<Integer>();

		count = 0;
		Structure structure = new Structure();
		startNode = new Node(0, 0);

		if (stage.getLoadCaseList() != null && !stage.getLoadCaseList().isEmpty()) {
			stage.getLoadCaseList().stream().forEach((LoadCase loadcase) -> {

				LoadGroup lg = new LoadGroup(loadcase.getName());
				structure.addLoadGroup(lg);
			});
		}

		subFrame.getBeamList().stream().forEach((Beam beam) -> {

			Node endNode = new Node(beam.getSpan(), startNode.get_x());
			beamElement = new Element(startNode, endNode);

			if (stage.getLoadCaseList() != null && !stage.getLoadCaseList().isEmpty()) {
				stage.getLoadCaseList().stream().forEach((LoadCase loadcase) -> {

					LoadGroup lg = new LoadGroup(loadcase.getName());
					if (loadcase.getUdlList() != null && !loadcase.getUdlList().isEmpty()) {
						loadcase.getUdlList().stream().forEach((UDL udl) -> {
							if (udl.getBeamIdList().contains(beam.getBeamId())) {
								Fem_UDL f_udl = new Fem_UDL(udl.getValue(), lg);
								beamElement.addUdl(f_udl);
							}
						});
					}

				});
			}

			structure.addElement(beamElement);
			beamElementIndexes.add(structure.getElementList().indexOf(beamElement));
			Node topEnd = new Node(startNode.get_x(), subFrame.getTopColumnList().get(count).getColumnHeight());
			topEnd.setSupport(true, true, true);
			Element topColumnElement = new Element(startNode, topEnd);
			structure.addElement(topColumnElement);
			Node bottomEnd = new Node(startNode.get_x(),
					-(subFrame.getBottomColumnList().get(count).getColumnHeight()));
			bottomEnd.setSupport(true, true, true);
			Element bottomColumnElement = new Element(startNode, bottomEnd);
			structure.addElement(bottomColumnElement);
			startNode = endNode;
			count++;
		});

		Node topNode = new Node(startNode.get_x(), subFrame.getTopColumnList().get(count).getColumnHeight());
		topNode.setSupport(true, true, true);
		Element topColumnElement = new Element(startNode, topNode);
		structure.addElement(topColumnElement);

		Node bottomNode = new Node(startNode.get_x(), -(subFrame.getBottomColumnList().get(count).getColumnHeight()));
		bottomNode.setSupport(true, true, true);
		Element bottomColumnElement = new Element(startNode, bottomNode);
		structure.addElement(bottomColumnElement);

		structure.solve();
		// ObjectMapper m = new ObjectMapper();
		// try {
		// System.out.println(m.writeValueAsString(structure.getElementList() + "el
		// list"));
		// } catch (JsonProcessingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		counter = 0;
		System.out.println(beamElementIndexes.size() + " beam size");
		beamElementIndexes.stream().forEach(b_index -> {

			structure.getElementList().get(b_index).getElementResultList().stream().forEach(elementResult -> {

				EndForces endForces = new EndForces();
				endForces.setFx_far(elementResult.getFx_far());
				endForces.setFx_near(elementResult.getFx_near());
				endForces.setFy_far(elementResult.getFy_far());
				endForces.setFy_near(elementResult.getFy_near());
				endForces.setMz_far(elementResult.getMz_far());
				endForces.setMz_near(elementResult.getMz_near());

				Optional<LoadCase> lc = stage.getLoadCaseList().stream().filter(e -> {
					return e.getName() == elementResult.getLoadGroup().getName();
				}).findFirst();
				endForces.setLoadCaseId(lc.get().getLoadCaseId());
				endForces.setStageId(stage.getStageId());
				if (subFrame.getBeamList().get(counter).getEndForceList() != null) {
					subFrame.getBeamList().get(counter).addEndForce(endForces);
				} else {
					ArrayList endForceList = new ArrayList<EndForces>();
					endForceList.add(endForces);
					subFrame.getBeamList().get(counter).setEndForceList(endForceList);
				}

			});
			counter++;

		});
		System.out.println("breakpoint");

	}

	void calculateBeamSectionProperties(Project project) {

		ArrayList<BeamSectionPropertyAtStage> beamSectionPropertyAtStageList = new ArrayList<BeamSectionPropertyAtStage>();

		project.getAnalysisStagesList().stream().forEach((AnalysisStage stage) -> {

			project.getBeamSectionList().stream().forEach((BeamSection section) -> {

				section.getPhaseList().stream().forEach((SectionPhase phase) -> {

								if (stage.getStageId() >= phase.getAddedStageID()) {
			
									double fcu_t = phase.getFcu_developementOrder().get(stage.getStageId() - 1);
									double fcu_28 = phase.getFcu_developementOrder()
											.get(phase.getFcu_developementOrder().size() - 1);
			
									switch (phase.getShapeType()) {
			
									case Rectangle:
										currentShape = new Rectangle(phase.getShapeParameters().get(0),
												phase.getShapeParameters().get(1), HelperUtil.getEc(fcu_t, fcu_28), 24);
										break;
									case InvertedTshape:
										currentShape = new InvertedTee(phase.getShapeParameters().get(0),
												phase.getShapeParameters().get(1), phase.getShapeParameters().get(2),
												phase.getShapeParameters().get(3));
										break;
									case Lshape:
										break;
									default:
										break;
			
									}

										if (phase.getPhaseId() == 1) {
				
											beamSectionPropertyAtStageList
													.add(new BeamSectionPropertyAtStage(stage.getStageId(), currentShape));
				
										} else {
				
											beamSectionPropertyAtStageList.add(new BeamSectionPropertyAtStage(stage.getStageId(),
													new CompositeShape(previousShape, currentShape)));
										}				
										previousShape = currentShape;
									}
									

				});
				
				section.setBeamSectionPropertyAtStageList(beamSectionPropertyAtStageList);
			});

		});

	}

}
