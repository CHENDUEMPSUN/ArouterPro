package com.example.lib_base.mvvm.bean;

import java.util.List;


public class TestBean {
    /**
     * count : 1
     * start : 0
     * subjects : [{"casts":[{"avatars":{"large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p230.jpg"},"name":"弗兰克·德拉邦特"},{"avatars":{"large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17525.jpg"},"name":"蒂姆·罗宾斯"},{"avatars":{"large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34642.jpg"},"name":"摩根·弗里曼"}],"comments_count":222527,"countries":["美国"],"directors":[{"avatars":{"large":null},"name":"弗兰克·德拉邦特"}],"genres":["剧情","犯罪"],"id":3,"images":{"large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.jpg"},"original_title":"肖申克的救赎 The Shawshank Redemption","rating":{"average":9.6,"max":10,"min":0,"stars":"50"},"reviews_count":5794,"summary":"","title":"肖申克的救赎 The Shawshank Redemption","warning":"数据来源于网络整理，仅供学习，禁止他用。如有侵权请联系公众号：小楼昨夜又秋风。我将及时删除。","wish_count":98814,"year":1994}]
     * total : 250
     */

    private int count;
    private int start;
    private int total;
    private List<SubjectsBean> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    public static class SubjectsBean {
        /**
         * casts : [{"avatars":{"large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p230.jpg"},"name":"弗兰克·德拉邦特"},{"avatars":{"large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17525.jpg"},"name":"蒂姆·罗宾斯"},{"avatars":{"large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34642.jpg"},"name":"摩根·弗里曼"}]
         * comments_count : 222527
         * countries : ["美国"]
         * directors : [{"avatars":{"large":null},"name":"弗兰克·德拉邦特"}]
         * genres : ["剧情","犯罪"]
         * id : 3
         * images : {"large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.jpg"}
         * original_title : 肖申克的救赎 The Shawshank Redemption
         * rating : {"average":9.6,"max":10,"min":0,"stars":"50"}
         * reviews_count : 5794
         * summary :
         * title : 肖申克的救赎 The Shawshank Redemption
         * warning : 数据来源于网络整理，仅供学习，禁止他用。如有侵权请联系公众号：小楼昨夜又秋风。我将及时删除。
         * wish_count : 98814
         * year : 1994
         */

        private int comments_count;
        private int id;
        private ImagesBean images;
        private String original_title;
        private RatingBean rating;
        private int reviews_count;
        private String summary;
        private String title;
        private String warning;
        private int wish_count;
        private int year;
        private List<CastsBean> casts;
        private List<String> countries;
        private List<DirectorsBean> directors;
        private List<String> genres;

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public int getReviews_count() {
            return reviews_count;
        }

        public void setReviews_count(int reviews_count) {
            this.reviews_count = reviews_count;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWarning() {
            return warning;
        }

        public void setWarning(String warning) {
            this.warning = warning;
        }

        public int getWish_count() {
            return wish_count;
        }

        public void setWish_count(int wish_count) {
            this.wish_count = wish_count;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public List<CastsBean> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsBean> casts) {
            this.casts = casts;
        }

        public List<String> getCountries() {
            return countries;
        }

        public void setCountries(List<String> countries) {
            this.countries = countries;
        }

        public List<DirectorsBean> getDirectors() {
            return directors;
        }

        public void setDirectors(List<DirectorsBean> directors) {
            this.directors = directors;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public static class ImagesBean {
            /**
             * large : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.jpg
             */

            private String large;

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }
        }

        public static class RatingBean {
            /**
             * average : 9.6
             * max : 10
             * min : 0
             * stars : 50
             */

            private double average;
            private int max;
            private int min;
            private String stars;

            public double getAverage() {
                return average;
            }

            public void setAverage(double average) {
                this.average = average;
            }

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
            }
        }

        public static class CastsBean {
            /**
             * avatars : {"large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p230.jpg"}
             * name : 弗兰克·德拉邦特
             */

            private AvatarsBean avatars;
            private String name;

            public AvatarsBean getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBean avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public static class AvatarsBean {
                /**
                 * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p230.jpg
                 */

                private String large;

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }
            }
        }

        public static class DirectorsBean {
            /**
             * avatars : {"large":null}
             * name : 弗兰克·德拉邦特
             */

            private AvatarsBeanX avatars;
            private String name;

            public AvatarsBeanX getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBeanX avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public static class AvatarsBeanX {
                /**
                 * large : null
                 */

                private Object large;

                public Object getLarge() {
                    return large;
                }

                public void setLarge(Object large) {
                    this.large = large;
                }
            }
        }
    }
}
