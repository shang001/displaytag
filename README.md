DisplayTag
==========

## Why this repository?

This is a fork of the SVN displaytag repository at http://svn.code.sf.net/p/displaytag/code/

I did it because the SVN repository seems dead, and I wanted to share the "View all results" feature I implemented.

The display tag library is an open source suite of custom tags for JSP that provide high-level web presentation patterns which will work in an MVC model.

## Structure of the repository

I converted the SVN repository to a git repository: all the history of the original 
repository is then saved.

## How did I convert the SVN repository?

1. First, clone the SVN repository into a git one which will be only temporary. I do this in a ~/git folder containing all my git repositories
- `git svn clone --stdlayout http://svn.code.sf.net/p/displaytag/code temp-displaytag`
2. Then, I'm connecting to a server which will contain the final repository. Create a bare remote repository.
- `git init --bare displaytag.git`
3. Make the symbolic reference HEAD point to refs/heads/trunk (default branch in SVN, instead of master)
- `cd displaytag.git`
- `git symbolic-ref HEAD refs/heads/trunk`
4. I come back to the temporary repository to push it to the remote one.
- `cd temp-displaytag`
- Add remote repository
- `git remote add origin login@remotehost:/path/to/git/displaytag.git`
- Configure push
- `git config remote.origin.push 'refs/remotes/*:refs/heads/*'`
- Push to the remote
- `git push origin`
- Delete the temporary repository
- `rm -rf temp-displaytag/`
5. Switch to the remote repository and rename the `trunk`branch to `master`
- `git branch -m trunk master`
6. git svn converts SVN tags to branches: you have to convert them to real git tags
- `git for-each-ref --format='%(refname)' refs/heads/tags | cut -d / -f 4 | while read ref;`
- `do git tag "$ref" "refs/heads/tags/$ref"; git branch -D "tags/$ref"; done`

## How did I pushed the repository on Github?

I used Eclipse + m2e plugin for Maven.

1. Cloned this remote repository in my local repositories folder.
2. Overwrited the working directory of this repository with the files I had in my Eclipse workspace: then git will see that the workdir has differences with the repository.
3. Created a repository in Github.com
4. Pushed my public key on my Github account
5. Added a remote repository `github` for the local repository I just cloned: then I can accept the RSA public key from github.
6. Pushed all the branches and tags to the `github` repository
