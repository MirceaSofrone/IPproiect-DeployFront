import { ForumComment } from "./forum-comment";

export class ForumPost {
    postID: string;
    content: string;
    author: {
        userID: string;
        firstName: string;
        lastName: string;
    };
    comments: ForumComment[];
    likes: { userID: string; }[];
    reports: { userID: string; }[];
}
