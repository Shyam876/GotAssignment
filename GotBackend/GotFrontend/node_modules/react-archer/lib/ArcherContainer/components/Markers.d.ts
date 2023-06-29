import { ShapeType } from '../../types';
import { SourceToTargetsArrayType } from '../ArcherContainer.types';
/** Generates all the markers
 * We want one marker per arrow so that each arrow can have
 * a different color or size
 * */
export declare const ArrowMarkers: ({ sourceToTargetsMap, endShape, strokeColor, uniqueId, }: {
    sourceToTargetsMap: Record<string, SourceToTargetsArrayType>;
    endShape: ShapeType;
    strokeColor: string;
    uniqueId: string;
}) => JSX.Element;
