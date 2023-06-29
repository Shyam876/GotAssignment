import React from 'react';
import { RelationType } from '../types';
declare type ArcherElementProps = {
    /**
     * The id that will identify the Archer Element.
     */
    id: string;
    relations?: Array<RelationType>;
    children: React.ReactElement<React.ComponentProps<any>, any>;
};
declare const ArcherElement: ({ id, relations, children }: ArcherElementProps) => React.FunctionComponentElement<any>;
export default ArcherElement;
