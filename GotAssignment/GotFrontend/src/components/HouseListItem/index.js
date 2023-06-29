import React, { useState } from 'react'
import {
    AccordionItem,
    AccordionButton,
    AccordionPanel,
    AccordionIcon,
    Box,
  } from '@chakra-ui/react'

import FamilyTreeComponent from '../FamilyTreeComponent'
import { GetRequests } from '../../util/ApiUtils';

function HouseListItem(props) {

  //Initiale tree state
  const [familyTree, setFamilyTree] = useState({characterName: "#", spouse : [], children:[], relations: []});
  
  //Fetch familytree for particular house on accordian expand button clicked
  const fetchFamilyTreeData = () => {
    
    GetRequests("/api/characters/familytree/"+props.element.name).then(response => {
      if(response.status=="success")
        setFamilyTree(response.data)
    })

  }

  return (
    <AccordionItem   key={props.i}>
        <h2>
            <AccordionButton onClick={fetchFamilyTreeData}>
            <Box as="span" flex='1' textAlign='left'>
                {props.element.name}
            </Box>
            <AccordionIcon />
            </AccordionButton>
        </h2>
        <AccordionPanel pb={props.i}>
        <Box
            overflowY="auto"
            css={{
                '&::-webkit-scrollbar': {
                width: '4px',
                },
                '&::-webkit-scrollbar-track': {
                width: '6px',
                },
                '&::-webkit-scrollbar-thumb': {
                scrollbarColor: 'red green',
                borderRadius: '24px',
                },
            }}
            ><FamilyTreeComponent key={props.i} data={familyTree} fetchFamilyTreeData={fetchFamilyTreeData}/></Box>
        </AccordionPanel>
    </AccordionItem>
  )
}

export default HouseListItem