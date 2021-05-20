export class ForumComment {
    commentID: string;
    forumPost: {
        postID: string;
    };
    author: {
        userID: string;
        firstName: string;
        lastName: string;
    };
    content: string;
}
