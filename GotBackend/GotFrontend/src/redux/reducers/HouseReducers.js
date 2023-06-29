


const initialHousesState = {
    houses : [

    ]
}


export const houseDetails = (state = initialHousesState, action) => {
    switch(action.type) {
        case "ADD_HOUSES" : 
            return {...state, houses: action.payload};
        
         case "ADD_HOUSE_FAMILY_TREE" : 
            state.houses.filter(element => element.name == action.payload.houseName)[0].tree = action.payload.tree;
            return state;
        
        default : 
            return state
    }
}

