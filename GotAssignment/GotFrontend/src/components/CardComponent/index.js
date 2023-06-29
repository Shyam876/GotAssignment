import { Box, Card, Heading, StackDivider, Text, Stack, Center } from '@chakra-ui/react'
import React from 'react'
import hollowHeart from '../../assets/hollow_heart.png'
import filledHeart from '../../assets/filled_heart.png'
import { GetRequests } from '../../util/ApiUtils'



function CardComponent(props) {

    const toggleFavorite = (id) => {
        GetRequests("/api/characters/"+id+"/favorite").then(response => {
            if(response.status=="success")
                props.fetchFamilyTreeData();
        })
    }

    return (
        <Card
        maxWidth="200px" maxHeight="300px" alignContent='center' padding={5} 
        overflowY="auto"  
        marginX='50px'
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
        }} >
            <Box style={{display: "flex", justifyContent: "space-between", flexDirection: 'row'}}>
                <Heading size="xs">{props.heading}</Heading>
                <Box>
                { props.showFavorite==true ? props.favorite == false ? 
                    <img src={hollowHeart} alt="hollowHeart" height={20} width={20} style={{marginLeft : 10}} onClick={() => toggleFavorite(props.element.id)}/>
                    :
                    <img src={filledHeart} alt="filled" height={20} width={20} style={{marginLeft : 10}} onClick={() => toggleFavorite(props.element.id)}/>
                    : null    
                }
                </Box>
            </Box>

            <Box>
                <Stack divider={<StackDivider />} spacing='2'>

                    {
                        props.content.map((relation, i) => (
                            <Box>
                                <Text pt='2' fontSize='sm'>
                                    {relation.key} : {relation.value}
                                </Text>
                            </Box>
                        ))
                    }
                    
                </Stack>
            </Box>
        </Card>
    )
}

export default CardComponent