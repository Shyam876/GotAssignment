import { Box, Flex } from '@chakra-ui/react'
import React from 'react'
import { ArcherContainer, ArcherElement } from "react-archer";
import Queue from "../../libraries/Queue"
import CardComponent from '../CardComponent'


//root element and following row element styles
const rootStyle = { margin: "20px 0px", display: "flex", justifyContent: "center" };
const rowStyle = {
    margin: "100px 0",
    display: "flex",
    justifyContent: "space-between"
};
const boxStyle = { padding: "10px", border: "1px solid black", display: "flex", justifyContent: "space-between" };


//For every card, creating a childrens target and spouse targetm that the current card should point to
//the children target will be connected through a dotted red line, and spouse target with blue line
const constructTargetObjects = (data) => {
    var targetObjects = [];
    data.children.map((element, i) => targetObjects.push({ targetId : element.characterName, targetAnchor: "top", sourceAnchor: "bottom", style: { strokeDasharray: "5,5" }}))
    data.spouse.map((element, i) => targetObjects.push({ targetId : element.characterName, targetAnchor: "left", sourceAnchor: "right", style: { strokeColor: "blue", strokeWidth: 1 }}))
    
    return targetObjects;
}


//Here we are performing a level order traversal of the given tree
//initially we are taking a head node, adding it to first level
//then in following iterations, we will add current node and its spouses in current array of objects depicting current level
//and adding childrens to queue, to be used in following iterations
const constructTree = (props) => {

    //Initializing head node with first element in the tree
    let headNode = props.data

    //Create a queue, add current head node and its spouse relations
    let q = new Queue();
    q.enqueue(headNode)
    headNode.spouse.map((node, i) => headNode.enqueue(node));
    q.enqueue(null);

    //Resulting traversed array will contain array of array objects on multiple levels
    var treeTraversed = []

    while(!q.isEmpty() && q.peek()!=null){

        //Objects on current level
        let currentLevelNodes = []
        let currentNode = q.dequeue();

        while(currentNode!=null){

            currentLevelNodes.push({ node: currentNode, mapping: constructTargetObjects(currentNode), relations: currentNode.relations})

            currentNode.children.map((node, i) => {
                q.enqueue(node)
                node.spouse.map((spouseNode, i) => q.enqueue(spouseNode))
            })

            currentNode = q.dequeue();

        }

        q.enqueue(null);
        treeTraversed.push(currentLevelNodes)
        

    }

    return (

        //Finally, after creating array of arrays, containing objects on multiple levels
        //We have already added mappings that specify the source and destination for every node object
        //We will just create a card on every specified level
        <ArcherContainer strokeColor="red">
            
            {

                treeTraversed.map((levelTraversed, i) => (

                    i==0 ? 

                    <div style={rootStyle}>
                    {levelTraversed.map((nodeObject, i)=> (
                        <ArcherElement
                        id={nodeObject.node.characterName}
                        key={nodeObject.node.characterName}
                        relations={nodeObject.mapping}
                        
                        >
                            <div style={boxStyle}>{nodeObject.node.characterName}</div>
                        </ArcherElement>
                    ))}
                    </div>

                    : 

                    <div style={rowStyle}>
                    {levelTraversed.map((nodeObject, i)=> (
                        <ArcherElement
                        id={nodeObject.node.characterName}
                        key={nodeObject.node.characterName}
                        relations={nodeObject.mapping}
                        
                        >
                            <Box> <CardComponent heading={nodeObject.node.characterName} favorite={nodeObject.node.favorite} content={nodeObject.relations} fetchFamilyTreeData={props.fetchFamilyTreeData} showFavorite={true}/> </Box>
                        </ArcherElement>
                    ))}
                    </div>
                ))
            }

        </ArcherContainer>
    )

}


function FamilyTreeComponent(props) {

    let instructions = [
        { key : "Parent-Child Relation", value: "Dotted-red line" },
        { key : "Husband Relation", value: "Blue-line" }
    ]

  return (

    <Box>
        <CardComponent heading="Instructions" content={instructions} showFavorite={false} />
        <Flex minWidth='max-content' alignItems='center'>
            {constructTree(props)}
        </Flex>

    </Box>
  )
}

export default FamilyTreeComponent