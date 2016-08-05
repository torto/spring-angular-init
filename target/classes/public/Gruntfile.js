module.exports = function(grunt) {
  grunt.initConfig({
    less: {
      development: {
        options: {
          compress: true,
          yuicompress: true,
          optimization: 2
        },
        files: {
          "css/style.css": "css/style.less"
        }
      }
    },
    watch: {
      dist: {
        files: ['css/*.less'],
        tasks: ['less']
      }
    }
  });

  grunt.registerTask('watch', ['watch']);

  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-contrib-less');
};
