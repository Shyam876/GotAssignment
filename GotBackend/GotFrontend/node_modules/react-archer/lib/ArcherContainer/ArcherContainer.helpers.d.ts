import { ValidShapeTypes, LineType, SourceToTargetType, ShapeType, EntityRelationType } from '../types';
import { SourceToTargetsArrayType } from './ArcherContainer.types';
export declare const getEndShapeFromStyle: (shapeObj: LineType) => ValidShapeTypes;
export declare const getSourceToTargets: (sourceToTargetsMap: Record<string, SourceToTargetsArrayType>) => SourceToTargetType[];
export declare const createShapeObj: (style: LineType, endShape: ShapeType) => {
    arrow: {
        arrowLength?: number | undefined;
        arrowThickness?: number | undefined;
    };
} | {
    circle: {
        radius?: number | undefined;
        fillColor?: string | undefined;
        strokeColor?: string | undefined;
        strokeWidth?: number | undefined;
    };
};
/** Generates an id for an arrow marker
 * Useful to have one marker per arrow so that each arrow
 * can have a different color!
 * */
export declare const getMarkerId: (uniqueId: string, source: EntityRelationType, target: EntityRelationType) => string;
