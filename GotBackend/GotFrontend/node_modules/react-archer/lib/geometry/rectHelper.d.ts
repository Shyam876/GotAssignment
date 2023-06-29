import Vector2 from './Vector2';
import { AnchorPositionType } from '../types';
export declare const getPointFromElement: (element: HTMLDivElement | null | undefined) => Vector2 | null;
export declare const getPointCoordinatesFromAnchorPosition: (position: AnchorPositionType, index: string, parentCoordinates: Vector2, refs: Record<string, HTMLElement>) => Vector2 | null;
