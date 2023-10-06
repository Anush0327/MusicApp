import { Artist } from "./artist";

export interface Song {
    title: string;
    artists: Artist[];
    albumName: string;
    isLiked: boolean;
}