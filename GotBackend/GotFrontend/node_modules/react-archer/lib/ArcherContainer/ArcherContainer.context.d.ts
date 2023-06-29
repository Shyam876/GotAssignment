import React from 'react';
import { SourceToTargetType } from '../types';
export declare type ArcherContainerContextType = {
    registerChild: (id: string, element: HTMLElement) => void;
    registerTransitions: (id: string, sourceToTarget: SourceToTargetType[]) => void;
    unregisterChild: (id: string) => void;
    unregisterTransitions: (id: string) => void;
};
export declare const ArcherContainerContext: React.Context<ArcherContainerContextType | null>;
export declare const ArcherContainerContextProvider: React.Provider<ArcherContainerContextType | null>;
