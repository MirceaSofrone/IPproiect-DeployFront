import { FinalPost } from './final-post.model';

describe('FinalPost', () => {
  it('should create an instance', () => {
    expect(new FinalPost("0", "0", "0", "0", "0", "0")).toBeTruthy();
  });
});
