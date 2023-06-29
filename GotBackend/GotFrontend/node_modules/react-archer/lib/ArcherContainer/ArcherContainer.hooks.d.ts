/** Subscribe/unsubscribe to the DOM observer */
export declare const useObserveElements: (refs: Record<string, HTMLElement>, observer: ResizeObserver) => void;
/** Subscribe/unsubscribe to the resize window event */
export declare function useResizeListener(refreshScreen: () => void): void;
