import React, { useEffect } from 'react'

import {
  Accordion
} from '@chakra-ui/react'
import HouseListItem from '../HouseListItem'
import { useSelector, useDispatch } from 'react-redux';
import { GetRequests } from '../../util/ApiUtils';
import { addHouses } from '../../redux/actions/HouseActions';

function HouseList(props) {

    const houses = useSelector((state) => state.houseDetails.houses)
    const dispatch = useDispatch();


    //Fetch houses list from server
    useEffect(() => {
        GetRequests("/api/characters/houses").then(res => {
            if(res.status == "success")
                dispatch(addHouses(res.data))
        });
    
    }, [])

    return (
        <Accordion allowToggle>

            {houses.map((element, i) => (
                <HouseListItem key={i} element={element} i={i} />
            ))}

        </Accordion>
    )
}

export default HouseList