import { ArcherContainerContextType } from '../ArcherContainer/ArcherContainer.context';
import { RelationType, SourceToTargetType } from '../types';
export declare function assertContextExists(context: ArcherContainerContextType | null): asserts context is ArcherContainerContextType;
export declare const generateSourceToTarget: (id: string, relations: Array<RelationType>) => Array<SourceToTargetType>;
